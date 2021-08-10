var Fenrir = Fenrir || {};

Fenrir.MaskMoney = (function() {
	
	function MaskMoney() {
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}
	
	MaskMoney.prototype.enable = function() {
		this.decimal.maskMoney({ decimal: ',', thousands: '.' });
		this.plain.maskMoney({ precision: 0, thousands: '.' });
	}
	
	return MaskMoney;
	
}());

Fenrir.MaskLetter = (function() {
	
	function MaskLetter() {
		this.letter = $('.js-letra');		
	}
	
	MaskLetter.prototype.enable = function() {
		
		this.letter.on('keyup',onAceitaApenasLetras.bind(this));
		
	}
	
	function onAceitaApenasLetras(){
		if(this.letter){
			this.letter.val(this.letter.val().replace(/[^A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]/g,''));
		}
	}
	
	return MaskLetter;
	
}());

Fenrir.MaskPhoneNumber = (function() {
	
	function MaskPhoneNumber() {
		this.inputPhoneNumber = $('.js-phone-number');
	}
	
	MaskPhoneNumber.prototype.enable = function() {
		var maskBehavior = function (val) {
		  return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		};
		
		var options = {
		  onKeyPress: function(val, e, field, options) {
		      field.mask(maskBehavior.apply({}, arguments), options);
		    }
		};
		
		this.inputPhoneNumber.mask(maskBehavior, options);
	}
	
	return MaskPhoneNumber;
	
}());

Fenrir.MaskCep = (function() {
	
	function MaskCep() {
		this.inputCep = $('.js-cep');
	}
	
	MaskCep.prototype.enable = function() {
		this.inputCep.mask('00.000-000');
	}
	
	return MaskCep;
	
}());

Fenrir.MaskDate = (function() {
	
	function MaskDate() {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function() {
		try {
			this.inputDate.mask('00/00/0000',{placeholder: "__/__/____"});
			this.inputDate.datepicker({
				orientation: 'bottom left',
				language: 'pt-BR',
				autoclose: true,
			    todayHighlight: true
			});	
		}
		catch(err) {}
	}
		
	
	return MaskDate;
	
}());

Fenrir.Security = (function() {
	
	function Security() {
		this.token = $('input[name=_csrf]').val();
		this.header = $('input[name=_csrf_header]').val();
	}
	
	Security.prototype.enable = function() {
		$(document).ajaxSend(function(event, jqxhr, settings) {
			jqxhr.setRequestHeader(this.header, this.token);
		}.bind(this));
	}
	
	return Security;
	
}());

$(function() {
	var maskMoney = new Fenrir.MaskMoney();
	maskMoney.enable();
	
	var maskLetter = new Fenrir.MaskLetter();
	maskLetter.enable();
	
	
	var maskPhoneNumber = new Fenrir.MaskPhoneNumber();
	maskPhoneNumber.enable();
	
	var maskCep = new Fenrir.MaskCep();
	maskCep.enable();
	
	var maskDate = new Fenrir.MaskDate();
	maskDate.enable();
	
	var security = new Fenrir.Security();
	security.enable();
	
});