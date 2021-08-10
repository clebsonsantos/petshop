var Fenrir = Fenrir || {};

Fenrir.CorCadastroRapido = (function() {
	function CorCadastroRapido() {
		this.modal = $('#modalCadastroRapidoCor');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-cor-salvar-btn');
		this.form = this.modal.find('form');
		this.containerMessagemErro = $('.js-messagem-cadastro-rapido-cor');
		this.url = this.form.attr('action');
		this.inputDescricaoCor = $('#descricaoCor');
	}

	CorCadastroRapido.prototype.iniciar = function() {

		this.form.on('submit', function(event) {
			event.preventDefault()
		});
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));

	}

	function onModalShow() {
		this.inputDescricaoCor.focus();
	}

	function onModalClose() {
		this.inputDescricaoCor.val('');
		this.containerMessagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}

	function onBotaoSalvarClick() {
		var nomeCor = this.inputDescricaoCor.val().trim();
console.log(this.url);
		$.ajax({
			url : this.url,
			method : 'POST',
			contentType : 'application/json',
			data : JSON.stringify({
				descricao : nomeCor
			}),
			error : onErroSalvandoCor.bind(this),
			success : onCorSalvo.bind(this)

		});

		function onErroSalvandoCor(obj) {
			var mensagemErro = obj.responseText;
			this.containerMessagemErro.removeClass('hidden');
			this.containerMessagemErro
					.html('<span>' + mensagemErro + '</span>');
			this.form.find('.form-group').addClass('has-error');
		}

		function onCorSalvo(cor) {
			var comboCor = $('#corAnimal');
			comboCor.append('<option value=' + cor.id + '>' + cor.descricao
					+ '</option>');
			comboCor.val(cor.id);			
			comboCor.selectpicker('refresh');
			this.modal.modal('hide');
		}
	}
	return CorCadastroRapido;

}());

$(function() {
	
	var corCadastroRapido = new Fenrir.CorCadastroRapido();
	corCadastroRapido.iniciar();

});