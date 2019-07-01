/**
 * @author : mengmuzi
 * create at:  2019-07-01  21:47
 * @description:
 */
public enum CountryEnums {

    ONE(1,"韩"),
    TWO(2,"赵"),
    THREE(3,"楚"),
    FOUR(4,"燕"),
    FIVE(5,"齐"),
    SIX(6,"魏");

    private Integer retCode;

    private String retMsg;

    CountryEnums(Integer retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public static CountryEnums forEachCountryEnums(Integer index){
        for (CountryEnums element: values()) {
            if(element.getRetCode() == index){
                return element;
            }
        }
        return null;
    }




}
