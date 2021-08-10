var Fenrir = Fenrir || {};

Fenrir.CepConsultaLogradouro = (function() {
	function CepConsultaLogradouro() {
		this.inputCep = $('#cep');
		this.inputBairro = $('#bairro');
		this.inputLogradouro = $('#logradouro');
		this.comboEstado = $('#estado');
		this.comboCidade = $('#cidade');
		this.containerMessagemErro = $('.js-messagem-cadastro-rapido-cor');
		this.imgLoading = $('.js-img-loading');
	}

	CepConsultaLogradouro.prototype.iniciar = function() {
		reset.call(this);
		if(this.inputCep.val() != null){
			onIniciar.call(this);
		}
		this.inputCep.on('blur', onIniciar.bind(this));
		

	}

	function onIniciar() {
		var cepDescricao = this.inputCep.val().trim().replace('.','').replace('-','');
		
		if (cepDescricao) {
			var url = this.inputCep.data('url');

			var resposta = $.ajax({
				url : this.inputCep.data('url'),
				method : 'GET',
				contentType : 'application/json',
				data : {
					'logradouro' : cepDescricao
				},
				beforeSend : iniciarRequisicao.bind(this),
				complete : finalizarRequisicao.bind(this),
				error : onErroConsultarLogradouro.bind(this),

			});

			resposta.done(buscarLogradouro.bind(this));
		} else {
			reset.call(this)
		}
	}

	function onErroConsultarLogradouro(obj) {
		var mensagemErro = obj.responseText;
		this.containerMessagemErro.removeClass('hidden');
		this.containerMessagemErro.html('<span>' + mensagemErro + '</span>');
		habilitarCampos.call(this);

	}

	function buscarLogradouro(logradouro) {
		if (logradouro) {
			this.inputBairro.val(logradouro.bairro);
			this.inputLogradouro.val(logradouro.descricao);
			this.comboEstado.val(logradouro.cidade.estado.id);			
			this.comboEstado.selectpicker('refresh');

			this.comboCidade.html('<option value=' + logradouro.cidade.id + '>'
					+ logradouro.cidade.descricao + '</option>');
			this.comboCidade.val(logradouro.cidade.id);
			this.comboCidade.removeAttr('disabled')
			this.comboCidade.change();
			this.comboCidade.selectpicker('refresh');
			

			desabilitarCampos.call(this);

		} else {

			reset.call(this);
		}
	}

	function desabilitarCampos() {		
		this.inputBairro.attr('disabled', 'disabled');		
		this.comboEstado.attr('disabled', 'disabled');
		this.comboCidade.attr('disabled', 'disabled');
		this.comboCidade.selectpicker('refresh');
		this.comboEstado.selectpicker('refresh');

	}

	function habilitarCampos() {		
		this.inputBairro.removeAttr('disabled');		
		this.comboEstado.removeAttr('disabled');
		this.comboCidade.removeAttr('disabled');
		this.comboCidade.selectpicker('refresh');
		this.comboEstado.selectpicker('refresh');
	}

	function reset() {

		this.comboCidade.html('<option value="">Selecione a cidade</option>');
		this.comboCidade.val('');
		this.comboCidade.attr('disabled', 'disabled');
		this.comboCidade.selectpicker('refresh');
		this.inputBairro.val('');
		this.inputLogradouro.val('');
		finalizarRequisicao.call(this);
	}

	function iniciarRequisicao() {
		reset.call(this);
		this.imgLoading.show();
	}

	function finalizarRequisicao() {
		this.imgLoading.hide();
	}

	return CepConsultaLogradouro;

}());

$(function() {

	var cepConsultaLogradouro = new Fenrir.CepConsultaLogradouro();
	cepConsultaLogradouro.iniciar();

});