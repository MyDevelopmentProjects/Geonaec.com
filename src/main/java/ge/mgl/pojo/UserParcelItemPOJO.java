package ge.mgl.pojo;

/**
 * Created by user on 11/27/17.
 */
public class UserParcelItemPOJO {

    private Long category;
    private Double clientBuyAmount = 0d;
    private String trackingCode;
    private String store;
    private String productCode;

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Double getClientBuyAmount() {
        return clientBuyAmount;
    }

    public void setClientBuyAmount(Double clientBuyAmount) {
        this.clientBuyAmount = clientBuyAmount;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
