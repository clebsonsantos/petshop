var Fenrir = Fenrir || {};

Fenrir.UploadFoto = (function() {

	function UploadFoto() {
		this.inputNomeFoto = $('input[name=imagem]');
		this.inputContentType = $('input[name=contentType]');
		this.novaFoto = $('input[name=novaFoto]');

		this.htmlFotoEntidadeTemplate = $('#foto-entidade').html();
		this.template = Handlebars.compile(this.htmlFotoEntidadeTemplate);

		this.containerFotoAnimal = $('.js-foto-animal');

		this.uploadDrop = $('#uploadDrop');
		

	}

	UploadFoto.prototype.iniciar = function() {
		var settings = {
			type : 'json',
			filelimit : 1,
			allow : '*.(jpg|jpeg|png)',
			action : this.containerFotoAnimal.data('url-fotos'),
			complete : onUploadCompleto.bind(this),
			beforeSend : onConfigToken.bind(this)
		}

		UIkit.uploadSelect($('#uploadSelect'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);

		if (this.inputNomeFoto.val()) {
			renderizarFoto.call(this, {nome : this.inputNomeFoto.val(), contentType : this.inputContentType.val()});
		}

	}

	function onUploadCompleto(resposta) {
		this.novaFoto.val('true');
		renderizarFoto.call(this,resposta);
	}
	
	function renderizarFoto(resposta){
		this.inputNomeFoto.val(resposta.nome);
		this.inputContentType.val(resposta.contentType);

		this.uploadDrop.addClass('hidden');

		var foto = '';
		if(this.novaFoto.val() == 'true'){
			foto = 'temp/';
		}
		
		foto += resposta.nome;
		
		var htmlFotoEntidade = this.template({
			foto : foto
		});

		this.containerFotoAnimal.append(htmlFotoEntidade);

		$('.js-remove-foto').on('click', onRemoverFoto.bind(this));
	}

	function onRemoverFoto() {
		this.uploadDrop.removeClass('hidden');
		this.inputNomeFoto.val('');
		this.inputContentType.val('');
		$('.js-foto-entidade').remove();
	}

	function onConfigToken(xhr) {
		var token = $('input[name=_csrf]').val();
		var header = $('input[name=_csrf_header]').val();
		xhr.setRequestHeader(header, token);
	}

	return UploadFoto;

})();

$(function() {

	var uploadFoto = new Fenrir.UploadFoto();
	uploadFoto.iniciar();

})