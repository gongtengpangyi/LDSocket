package frz.ld.helper.stringcast;

import frz.ld.helper.LdReflectHelper.StringCast;

public class LdStringCastBoolean implements StringCast {

	@Override
	public Object cast(String value) {
		return new Boolean(value);
	}

}
