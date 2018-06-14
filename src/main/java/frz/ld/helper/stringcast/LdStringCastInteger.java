package frz.ld.helper.stringcast;

import frz.ld.helper.LdReflectHelper.StringCast;

public class LdStringCastInteger implements StringCast {

	@Override
	public Object cast(String value) {
		return new Integer(value);
	}

}
