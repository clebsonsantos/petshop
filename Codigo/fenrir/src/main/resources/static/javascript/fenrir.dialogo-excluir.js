Fenrir = Fenrir || {};

Fenrir.DialogoExcluir = (function() {

	function DialogoExcluir() {
		this.exclusaoBtn = $('.js-exclusao-btn');
		this.excluir = true;
		
	}

	DialogoExcluir.prototype.iniciar = function() {
		this.exclusaoBtn.on('click', onExcluirClicado.bind(this));
		
		if(window.location.search.indexOf('excluido') > -1){
			swal('Pronto!', 'Excluído com sucesso!', 'success');
		}else if(window.location.search.indexOf('cancelado') > -1){
			swal('Pronto!', 'Cancelado com sucesso!', 'success');
		}
	}
	
	function onExcluirClicado(event){		
		event.preventDefault();
		var botaoClicado = $(event.currentTarget);
		var url = botaoClicado.data('url');
		var objeto = botaoClicado.data('objeto');
		this.excluir = botaoClicado.data('excluir');
		
		var text = '';
		var textConfirm = '';
		if(this.excluir == true){
			text = 'Excluir "' + objeto + '"? Você não poderá recuperar depois.';
			textConfirm = 'Sim, Excluir agora!';
		}else{
			text = 'Cancelar "' + objeto + '"? Você pode alterar depois.';
			textConfirm = 'Sim, Cancelar agora!';
		}
		
		
		swal({
			 title: 'Tem certeza?',
			 type: 'warning',
			  text: text,			  
			  showCancelButton: true,
			  confirmButtonColor: '#DD6B55',
			  confirmButtonText: textConfirm,
			  closeOnConfirm: false
		}, onExcluirConfirmado.bind(this, url));	
		
		
	}
	function onExcluirConfirmado(url){
		$.ajax({
			url: url,
			method: 'DELETE',
			success: onExcluidoSucesso.bind(this),
			error: onErroExcluir.bind(this)
				
		});
	}
	
	function onExcluidoSucesso(){
		var urlAtual = window.location.href; 
		var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
		var novaUrl  = '';
		if(this.excluir == true){
			novaUrl  = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido';
		}else{
			novaUrl  = urlAtual.indexOf('cancelado') > -1 ? urlAtual : urlAtual + separador + 'cancelado';
		}
		
		window.location = novaUrl;	
		
	}
	
	function onErroExcluir(e){		
		swal('Oops!', e.responseText, 'error');		
	}

	return DialogoExcluir;

})();

$(function() {
	var dialogo = new Fenrir.DialogoExcluir();
	dialogo.iniciar();
});
