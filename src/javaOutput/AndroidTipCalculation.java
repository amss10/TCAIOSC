TCIOSACERROR Statements publicstructTipCalculation{letsubtotal:DoublelettipPercentage:IntletnumberInParty:Intpublicvartip:Double{return(Double(tipPercentage)*0.01)*subtotal}publicvartotal:Double{returnsubtotal+tip}publicvarperPerson:Double{returnnumberInParty<1?total:(total/Double(numberInParty))}publicinit(subtotal:Double,tipPercentage:Int,numberInParty:Int){assert(numberInParty>0)self.subtotal=subtotalself.tipPercentage=tipPercentageself.numberInParty=numberInParty}}

