var Fenrir = Fenrir || {};

Fenrir.ComboEspecie = (function() {

	function ComboEspecie() {
		this.combo = $('#especie');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}

	ComboEspecie.prototype.iniciar = function() {
		this.combo.on('change', onEspecieAlterado.bind(this));
	}

	function onEspecieAlterado() {
		this.emitter.trigger('alterado', this.combo.val());
	}

	return ComboEspecie;

}());

Fenrir.ComboRaca = (function() {

	function ComboRaca(comboEspecie) {
		this.comboEspecie = comboEspecie;
		this.combo = $('#racaAnimal');
		this.imgLoading = $('.js-img-loading');
		this.racaSelecionada = $('#racaSelecionada');

	}

	ComboRaca.prototype.iniciar = function() {
		if(this.racaSelecionada.val() != null){
			onEspecieAlterado.call(this);
		}else{
			reset.call(this);
		}
		this.comboEspecie.on('alterado', onEspecieAlterado.bind(this));
		
	}

	function onEspecieAlterado(evento, idEspecie) {
		if(this.racaSelecionada.val() != null){
			idEspecie = this.comboEspecie.combo.val(); 
		
		}
		if (idEspecie) {
			var resposta = $.ajax({
				url : this.combo.data('url'),
				method : 'GET',
				contentType : 'application/json',
				data : {
					'especie' : idEspecie
				},
				beforeSend : iniciarRequisicao.bind(this),
				complete : finalizarRequisicao.bind(this)
			});

			resposta.done(buscarRacaFinalizado.bind(this));

		} else {
			reset.call(this);
		}

	}

	function reset() {
		this.combo.html('<option value="">Selecione a raça</option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
		this.combo.selectpicker('refresh');
	}

	function buscarRacaFinalizado(racas) {
		var options = [];
		racas.forEach(function(raca) {
			options.push('<option value=' + raca.id + '>' + raca.descricao
					+ '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled')
		
		if(this.racaSelecionada.val() != null){
			this.combo.val(this.racaSelecionada.val());	
		}
		this.combo.selectpicker('refresh');

	}

	function iniciarRequisicao() {
		reset.call(this);
		this.imgLoading.show();
	}

	function finalizarRequisicao() {
		this.imgLoading.hide();
	}

	return ComboRaca;

}());

$(function() {

	var comboEspecie = new Fenrir.ComboEspecie();
	comboEspecie.iniciar();

	var comboRaca = new Fenrir.ComboRaca(comboEspecie);
	comboRaca.iniciar();

});