public class MobilePhone{
	
	private int uniqueId;
	private Boolean status;
	private Exchange baseStation;
	MobilePhone(int number){
		this.uniqueId = number;
	}

	public int number(){
		return this.uniqueId;
	}

	public Boolean status(){
		return this.status;
	}

	public void switchOn(){
		this.status = true;
	}
	public void switchOff(){
		this.status = false;
	}
	public Exchange location(){
		return this.baseStation;
	}
	
	public void setBase(Exchange baseStation)
	{
		this.baseStation = baseStation;
	}

}