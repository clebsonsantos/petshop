var Fenrir = Fenrir || {};

Fenrir.CartaoVacinaCadastroRapido = (function(){
	
	function CartaoVacinaCadastroRapido(){
		this.form = $('#formCadastroCartaoVacina');
		this.botaoAplicarAgora = this.form.find('.js-cartao-aplicar-agora-btn');
		this.botaoAplicarDepois = this.form.find('.js-cartao-aplicar-depois-btn');
		this.url = this.form.attr('action');
		this.containerMessagemErro = $('.js-messagem-cadastro-cartao');
		this.comboVacina = $('#vacina');
		this.inputDataRevacina = $('#data-revacina');
		this.inputObservacao = $('#observacao');
		this.inputProntuario = $('#id-prontuario');
		this.inputConsulta = $('#id-consulta');
		this.inputDose = $('#dose');	
		this.tabelaCartaoVacinaContainer = $('.js-cartao-vacina-container');
		
	}
	
	CartaoVacinaCadastroRapido.prototype.iniciar = function(){
		this.form.on('submit', function(event) {
			event.preventDefault()
		});
		this.botaoAplicarAgora.on('click', onBotaoAplicarAgoraClick.bind(this));
		this.botaoAplicarDepois.on('click', onBotaoAplicarDepoisClick.bind(this));
	}
	
	function onBotaoAplicarAgoraClick(){
		var idVacina = this.comboVacina.val().trim();
		var dataRevacina = this.inputDataRevacina.val().trim();
		var observacao = this.inputObservacao.val().trim();
		var dataAplicacao = new Date().toLocaleString(); 
		var idProntuario = this.inputProntuario.val().trim();
		var dose = this.inputDose.val().trim();
		var idConsulta = this.inputConsulta.val().trim();
		$.ajax({
			url : this.url,
			method : 'POST',
			contentType : 'application/json',
			data : JSON.stringify({
				idVacina : idVacina,
				dataRevacina : dataRevacina,
				observacao: observacao,
				dose: dose,
				dataAplicacao: dataAplicacao,
				idProntuario: idProntuario,
				idConsulta: idConsulta
			}),
			error : onErroSalvandoCartao.bind(this),
			success : onCartaoSalvo.bind(this)

		});
		this.containerMessagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
		
	}
	function onBotaoAplicarDepoisClick(){
		var idVacina = this.comboVacina.val().trim();
		var dataRevacina = this.inputDataRevacina.val().trim();
		var observacao = this.inputObservacao.val().trim();
		var idProntuario = this.inputProntuario.val().trim();
		var idConsulta = this.inputConsulta.val().trim();
		var dose = this.inputDose.val().trim();
		
		
	var resposta =	$.ajax({
			url : this.url,
			method : 'POST',
			contentType : 'application/json',
			data : JSON.stringify({
				idVacina : idVacina,
				dataRevacina : dataRevacina,
				observacao: observacao,
				dose: dose,
				idProntuario: idProntuario,
				idConsulta: idConsulta
		
			}),
			error : onErroSalvandoCartao.bind(this)
		});
		
		resposta.done(onCartaoSalvo.bind(this));
	}
	function onErroSalvandoCartao(html) {
		
		this.containerMessagemErro.removeClass('hidden');
		this.containerMessagemErro.html(html);
		this.form.find('.fr-required').addClass('has-error');
	}
	
	
	function onCartaoSalvo(html) {		
		if(html.indexOf("js-error") == -1){
			limparCampos.call(this);
			this.containerMessagemErro.addClass('hidden');
			this.form.find('.form-group').removeClass('has-error');
			this.tabelaCartaoVacinaContainer.html(html);	
		}else{
			onErroSalvandoCartao.call(this,html);		
		}
		
	}
	
	function limparCampos(){
		this.comboVacina.val("");
		this.comboVacina.selectpicker('refresh');
		this.inputDataRevacina.val("");
		this.inputObservacao.val("");
		this.inputDose.val("");
	}
	
	return CartaoVacinaCadastroRapido;
	
	
}());

$(function(){
	var cartaoVacinaCadastroRapido = new Fenrir.CartaoVacinaCadastroRapido();
	cartaoVacinaCadastroRapido.iniciar();
});
