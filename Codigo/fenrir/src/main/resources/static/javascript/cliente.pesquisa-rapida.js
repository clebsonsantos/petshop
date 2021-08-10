var Fenrir = Fenrir || {};

/**
 * Combobox de pesquisa do filtro
 */
Fenrir.ComboFiltro = (function() {

	function ComboFiltro() {
		this.combo = $('#filtro');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}

	ComboFiltro.prototype.iniciar = function() {
		this.combo.on('change', onFiltroAlterado.bind(this));
	}

	function onFiltroAlterado() {
		this.emitter.trigger('alterado', this.combo.val());
	}

	return ComboFiltro;
}());

/**
 * Campos de relaização de pesquisa
 */
Fenrir.CampoPesquisa = (function() {

	function CampoPesquisa(comboFiltro) {
		this.comboFiltro = comboFiltro;
		this.inputDescricao = $('.js-descricao-pesquisa');
		this.inputMask = $('.js-mask-pesquisa');
		this.labelDescricao = $('.js-label-pesquisa');
	}

	CampoPesquisa.prototype.iniciar = function() {
		this.comboFiltro.on('alterado', onFiltroAlterado.bind(this));
	}

	function onFiltroAlterado(evento, filtro) {

		var mascara = '';

		switch (filtro) {

		case 'cliente':
			this.labelDescricao.text('Cliente');
			this.inputDescricao.attr('placeholder', 'Nome do cliente');
			// this.inputDescricao.removeClass('js-plain');
			onAtivarDescricao.call(this);
			break;
		case 'animal':
			this.labelDescricao.text('Animal');
			this.inputDescricao.attr('placeholder', 'Nome do animal');
			// this.inputDescricao.removeClass('js-plain');
			onAtivarDescricao.call(this);
			break;
		case 'prontuario':
			this.labelDescricao.text('Nº Prontuário');
			this.inputDescricao.attr('placeholder', 'Nº Prontuário');
			onAtivarDescricao.call(this);
			// this.inputDescricao.addClass('js-plain');
			break;
		case 'cpf':
			this.labelDescricao.text('CPF');
			this.inputMask.attr('placeholder', 'Nº CPF');
			mascara = '000.000.000-00';
			onAtivarMask.call(this);
			break;
		case 'telefone':
			this.labelDescricao.text('Telefone');
			this.inputMask.attr('placeholder', 'Nº Telefone');
			mascara = '(00) 0000-00009';
			onAtivarMask.call(this)
			break;
		}

		function onAtivarMask() {
			this.inputMask.mask(mascara);
			//this.inputMask.val('');
			this.inputDescricao.val('');
			this.inputMask.show();
			this.inputDescricao.hide();
		}

		function onAtivarDescricao() {
			this.inputMask.hide();
			//this.inputDescricao.val('');
			this.inputMask.val('');
			this.inputDescricao.show();
		}

	}

	return CampoPesquisa;

}());

/**
 * Pesquisa de clientes
 */
Fenrir.PesquisaRapida = (function() {

	function PesquisaRapida(comboFiltro, campoPesquisa) {
		this.comboFiltro = comboFiltro.combo;
		this.campoPesquisaDescricao = campoPesquisa.inputDescricao;
		this.campoPesquisaMask = campoPesquisa.inputMask;
		this.messageErro = $('.js-mensagem-erro');
		this.buttonPesquisa = $('.js-pesquisa');
		this.inputCliente = $('#nomeCliente');
		this.pesquisaRapidaClientesModal = $("#pesquisaRapidaClientes");
		this.containerTableaPesquisa = $('#containerTabelaPesquisaRapidaClientes');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-cliente').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
	}

	PesquisaRapida.prototype.iniciar = function() {
		resetTabelaPesquisa.call(this);
		this.buttonPesquisa.on('click', onPesquisar.bind(this));
		this.pesquisaRapidaClientesModal.on('shown.bs.modal', onModalShow.bind(this));
		this.pesquisaRapidaClientesModal.on('hide.bs.modal', onModalHide.bind(this)); 
		this.campoPesquisaDescricao.on('keyup',onPesquisaTempoRealDescricao.bind(this));
		this.campoPesquisaMask.on('keyup',onPesquisaTempoRealMask.bind(this));
	}
	
	function onModalShow(){		
		this.campoPesquisaDescricao.focus();
		
	}
	
	function onModalHide(){
		resetTabelaPesquisa.call(this);
		this.campoPesquisaMask.val('');
		this.campoPesquisaDescricao.val('');
	}
	
	function onPesquisaTempoRealMask(){
		this.filtro = this.comboFiltro.val();
		this.campoMask = this.campoPesquisaMask.val();
		this.descricao = this.campoMask.replace('(','').replace(')','').replace('.','').replace('-','').replace(' ','');
		if(this.descricao.length >= 3){
			onConsultar.call(this);
		}else{
			resetTabelaPesquisa.call(this);
		}
	}
	
	
	
	
	
	function resetTabelaPesquisa(){
		var html = this.template(null);
		this.containerTableaPesquisa.html(html);
	}
	
	function resetInput(){
		
	}
	
	
	function onPesquisaTempoRealDescricao(){
		this.filtro = this.comboFiltro.val();
		this.descricao = this.campoPesquisaDescricao.val();
		 
		if(this.descricao.length >= 3){
			onConsultar.call(this);			
			
		}else{
			resetTabelaPesquisa.call(this);
		}
		
	}

	function onPesquisar() {
		this.filtro = this.comboFiltro.val();
		this.campoTexto = this.campoPesquisaDescricao.val();
		this.campoMask = this.campoPesquisaMask.val();
		this.descricao = '';

		if (this.filtro == 'cliente' || this.filtro == 'animal') {
			
			this.descricao = this.campoTexto;
			console.log(this.descricao);
		} else {
			this.descricao = this.campoMask.replace('(','').replace(')','').replace('.','').replace('-','').replace(' ','');
		}

		onConsultar.call(this);
	}

	function onConsultar() {
		var resposta = $.ajax({
			url : this.pesquisaRapidaClientesModal.find('form').attr('action'),
			method : 'GET',
			contentType : 'application/json',
			data : {
				'filtro' : this.filtro,
				'descricao' : this.descricao
			},
			success : onPesquisaConcluida.bind(this),
			error : onErroPesquisa.bind(this)
		});
		resposta.done(buscaFinalizada.bind(this));
	}

	function onPesquisaConcluida(resultado) {
		
		this.messageErro.addClass('hidden');
	}

	function buscaFinalizada(lista) {
		this.messageErro.addClass('hidden');
		
		var html = this.template(lista);
		this.containerTableaPesquisa.html(html);
		
		var tabelaClientePesquisaRapida = new Fenrir.TabelaClientePesquisaRapida(this.pesquisaRapidaClientesModal, this.inputCliente);
		tabelaClientePesquisaRapida.iniciar();
		
	}

	function onErroPesquisa() {
		this.messageErro.removeClass('hidden');
	}

	return PesquisaRapida;

}());


/**
 * Tabela para a pesquisa rapida
 */

Fenrir.TabelaClientePesquisaRapida = (function(){
	function TabelaClientePesquisaRapida (modal, inputCliente){
		this.cliente = $('.js-cliente-pesquisa-rapida');
		this.modalCliente = modal;
		this.inputCliente = inputCliente;
		this.inputCodigoCliente = $('#codigoProntuario');
		
		this.containerComponenteAnimaCliente = $('#containerComponenteAnimaCliente');
		this.htmlTabelaAnimalCliente= $('#tabela-animal-cliente').html();
		this.templateAnimalCliente = Handlebars.compile(this.htmlTabelaAnimalCliente);
		
		this.tabelaVazia = $('.js-sem-animais');
	}
	
	TabelaClientePesquisaRapida .prototype.iniciar = function(){
		this.cliente.on('click',onClienteSelecionado.bind(this));
		
	}
	
	function onClienteSelecionado(evento){
		this.modalCliente.modal('hide');
		this.tabelaVazia.addClass('hidden');
		var clienteSelecionado = $(evento.currentTarget);
		this.inputCliente.val(clienteSelecionado.data('nome-cliente'));
		this.inputCodigoCliente.val(clienteSelecionado.data('id-prontuario'));		
		
		var animal = {
				nomeAnimal : clienteSelecionado.data('nome-animal'),
				idAnimal : clienteSelecionado.data('id-animal'),
				vip : (clienteSelecionado.data('vip')? "VIP" : "COMUM"),
				raca : clienteSelecionado.data('raca'),
				pelagem : clienteSelecionado.data('pelagem')
				};
		
		
		var html = this.templateAnimalCliente(animal);
		this.containerComponenteAnimaCliente.html(html);
	}
	
	return TabelaClientePesquisaRapida ;
	
}());

/**
 * inicio do programa
 * 
 * @returns
 */
$(function() {

	var comboFiltro = new Fenrir.ComboFiltro();
	comboFiltro.iniciar();

	var campoPesquisa = new Fenrir.CampoPesquisa(comboFiltro);
	campoPesquisa.iniciar();

	var pesquisaRapida = new Fenrir.PesquisaRapida(comboFiltro, campoPesquisa);
	pesquisaRapida.iniciar();

});