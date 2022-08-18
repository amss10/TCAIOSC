import java.lang.String;

TCIOSACERROR Statements structStrictScanner{letscanner:Scannerinit(string:String){scanner=Scanner(string:string)scanner.charactersToBeSkipped=nil}varatEnd:Bool{returnscanner.isAtEnd}funcscanInteger()->Int?{varvalue:Int=0ifscanner.scanInt(&value){returnvalue}returnnil}funcscanDouble()->Double?{varvalue:Double=0ifscanner.scanDouble(&value){returnvalue}returnnil}}
public null integerValueForString(String s) 
{
TCIOSACERROR Patternscanner = null null)
 if(TCIOSACERROR letresult=scanner.scanInteger()) {
 if(scanner.atEnd()) {
return null;

}


}

return nil;

}
public null doubleValueForString(String s) 
{
TCIOSACERROR Patternscanner = null null)
 if(TCIOSACERROR letresult=scanner.scanDouble()) {
 if(scanner.atEnd()) {
return null;

}


}

return nil;

}
public boolean isValidIntegerString(String s) 
{
return null null) != nil;

}
public boolean isValidDoubleString(String s) 
{
return null null) != nil;

}





