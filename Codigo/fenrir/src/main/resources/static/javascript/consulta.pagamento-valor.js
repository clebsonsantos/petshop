var Fenrir = Fenrir || {};

Fenrir.CalcularValor = (function() {

	function CalcularValor() {
		this.labelTroco = $('#valor-troco');
		this.labelAdicional= $('#valor-adicional');
		this.labelTotal = $('#valor-total');
		this.inputValorPago = $('#valor-pago');
		this.inputValorDesconto = $('#valor-desconto');
		this.mensagem = $('.js-alert-troco');
		

	}

	CalcularValor.prototype.iniciar = function() {

		this.labelTroco.text('R$ 0,00');

		this.inputValorPago.on('keyup', calcularTroco.bind(this));
		this.inputValorDesconto.on('keyup', calcularTroco.bind(this));
	}

	function tratarValor(valor) {

		valor = Number(valor.replace("R$", "").replace(",", "")
				.replace(".", "")) / 100;
		return valor;
	}

	function calcularTroco() {

		if (!this.inputValorPago.val().trim()) {
			this.inputValorPago.val('0,00');
		}

		if (!this.inputValorDesconto.val().trim()) {
			this.inputValorDesconto.val('0,00');
		}

		var formatter = new Intl.NumberFormat('de-DE', {
			minimumFractionDigits : 2
		});

		var totalPagar = 0;

		var total = tratarValor(this.labelTotal.text());
		var valorPago = tratarValor(this.inputValorPago.val());
		var valorDesconto = tratarValor(this.inputValorDesconto.val());
		var valorAdicional = tratarValor(this.labelAdicional.text());
		if (!isNaN(valorPago) && !isNaN(valorDesconto)) {
			totalPagar = (valorPago + valorDesconto);
			var troco = totalPagar - (valorAdicional + total);			
			if(troco >= 0){
				this.mensagem.addClass("hidden");
			}else{
				this.mensagem.removeClass("hidden");
			}
			if (!isNaN(troco)){
				this.labelTroco.text(('R$ ' + formatter.format(troco)));
			}
			
		}

	}

	return CalcularValor;
}());

(function() {

	var calcularValor = new Fenrir.CalcularValor();
	calcularValor.iniciar();
})();
