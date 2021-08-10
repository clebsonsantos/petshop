var Fenrir = Fenrir || {};

Fenrir.ConsultaProntuario = (function() {

	function ConsultaProntuario() {
		this.prontuarioBtn = $('.js-prontuario-btn');
		this.prontuarioTimeline = $('.js-timeline-prontuario');
		this.imgLoading = $('.js-img-loading');
	}

	ConsultaProntuario.prototype.iniciar = function() {
		this.prontuarioBtn.on('click', onListarProntuario.bind(this));
	}
	
	function onListarProntuario(event){		
		this.imgLoading.show();
		event.preventDefault();
		var botaoClicado = $(event.currentTarget);
		this.prontuarioBtn.addClass("btn-default");
		this.prontuarioBtn.removeClass("btn-warning");
		botaoClicado.addClass("btn-warning");
		botaoClicado.removeClass("btn-default");
		
		var url = botaoClicado.data('url');
		var id = botaoClicado.data('objeto');
		var resposta = $.ajax({
			url : url,
			method : 'GET',
			contentType : 'application/json',
			data : {
				'id' : id
			}
			
			
		});

		resposta.done(buscarProntuarioFinalizado.bind(this));
		
	}
	
	
	function converterDate(consulta){
		
		var day = consulta.dataConsulta.dayOfMonth;
		var month = consulta.dataConsulta.monthValue - 1; // Month is 0-indexed
		var year = consulta.dataConsulta.year;

		var date = new Date(Date.UTC(year, month, day));
		
		return date.toLocaleString();

	}
	
	function buscarProntuarioFinalizado(html){
		this.imgLoading.hide();
		this.prontuarioTimeline.html(html);
		
	}
	
	

	return ConsultaProntuario ;
}());
$(function() {
	
	var consultaProntuario  = new Fenrir.ConsultaProntuario();
	consultaProntuario.iniciar();

});