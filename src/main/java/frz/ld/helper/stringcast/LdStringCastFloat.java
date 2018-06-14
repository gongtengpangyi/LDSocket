package frz.ld.helper.stringcast;

import frz.ld.helper.LdReflectHelper.StringCast;

public class LdStringCastFloat implements StringCast {

	
	@Override
	public Object cast(String value) {
		return new Float(value);
	}

}
