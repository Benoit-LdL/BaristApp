package com.example.baristapp.PersonalList;

public class CocktailModel {
    public String cocktailName;
    public String cocktailCategory;
    public String cocktailTime;

    public CocktailModel()
    {

    }
    public CocktailModel(String cocktailName,String cocktailCategory, String cocktailTime)
    {
        this.cocktailName = cocktailName;
        this.cocktailCategory = cocktailCategory;
        this.cocktailTime = cocktailTime;
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    public String getCocktailCategory() {
        return cocktailCategory;
    }

    public void setCocktailCategory(String cocktailCategory) {
        this.cocktailCategory = cocktailCategory;
    }


    public String getCocktailTime() {
        return cocktailTime;
    }

    public void setCocktailTime(String cocktailTime) {
        this.cocktailTime = cocktailTime;
    }
}
