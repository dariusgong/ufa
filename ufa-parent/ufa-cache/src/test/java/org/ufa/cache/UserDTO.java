package org.ufa.cache;
import org.ufa.core.model.BaseObject;

/**
 * <P>TODO</P>
 * @author Jonathan
 */
public class UserDTO extends BaseObject{

	/**
	 * TODO
	 */
	private static final long serialVersionUID = 6780090544107542213L;
	private String name;
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
