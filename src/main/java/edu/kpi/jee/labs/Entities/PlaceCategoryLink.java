package edu.kpi.jee.labs.Entities;

/**
 * @author Yevheniia Zubrych on 25.02.2018.
 */
public class PlaceCategoryLink {
    private int placeId;
    private int categoryId;

    public PlaceCategoryLink(int placeId, int categoryId) {
        this.placeId = placeId;
        this.categoryId = categoryId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
