package com.qilu.ec.main.sample.example_list;

import com.qilu.ec.main.sample.example_create.ExampleCreate_Data;

public class ExampleResponse_Data {
    private ExampleResponse_Data_Star[] stars;

    public ExampleResponse_Data(ExampleResponse_Data_Star[] stars) {
        this.stars = stars;
    }

    public ExampleResponse_Data_Star[] getStars() {
        return stars;
    }

    public void setStars(ExampleResponse_Data_Star[] stars) {
        this.stars = stars;
    }
}
