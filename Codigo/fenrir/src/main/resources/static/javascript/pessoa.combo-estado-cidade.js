var Fenrir = Fenrir || {};

Fenrir.comboEstado = (function() {

	function comboEstado() {
		this.combo = $('#estado');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}

	comboEstado.prototype.iniciar = function() {
		this.combo.on('change', onEstadoAlterado.bind(this));
	}

	function onEstadoAlterado() {
		this.emitter.trigger('alterado', this.combo.val());
	}

	return comboEstado;

}());

Fenrir.comboCidade = (function() {

	function comboCidade(comboEstado) {
		this.comboEstado = comboEstado;
		this.combo = $('#cidade');
		this.imgLoading = $('.js-img-loading');

	}

	comboCidade.prototype.iniciar = function() {
		reset.call(this);
		this.comboEstado.on('alterado', onEstadoAlterado.bind(this));
		
	}

	function onEstadoAlterado(evento, idEstado) {
		if (idEstado) {
			var resposta = $.ajax({
				url : this.combo.data('url'),
				method : 'GET',
				contentType : 'application/json',
				data : {
					'estado' : idEstado
				},
				beforeSend : iniciarRequisicao.bind(this),
				complete : finalizarRequisicao.bind(this)
			});

			resposta.done(buscarCidadeFinalizado.bind(this));

		} else {
			reset.call(this);
		}

	}

	function reset() {
		this.combo.html('<option value="">Selecione a cidade</option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
		this.combo.selectpicker('refresh');
	}

	function buscarCidadeFinalizado(cidades) {
		var options = [];
		cidades.forEach(function(cidade) {
			options.push('<option value=' + cidade.id + '>' + cidade.descricao
					+ '</option>');
		});

		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled')

		this.combo.selectpicker('refresh');

	}

	function iniciarRequisicao() {
		reset.call(this);
		this.imgLoading.show();
	}

	function finalizarRequisicao() {
		this.imgLoading.hide();
	}

	return comboCidade;

}());

$(function() {

	var comboEstado = new Fenrir.comboEstado();
	comboEstado.iniciar();

	var comboCidade = new Fenrir.comboCidade(comboEstado);
	comboCidade.iniciar();

});