package com.example.dubizzletest.api

import com.example.dubizzletest.model.ListResponse
import com.example.dubizzletest.model.ResultsItem
import com.example.dubizzletest.network.ApiService
import java.lang.Exception
import javax.inject.Inject

class FakeApiService @Inject constructor() : ApiService {

    var failUserApi: Boolean = false
    var wrongResponse: Boolean = false

    override suspend fun getList(): ListResponse {
        if (failUserApi) throw Exception("Api failed")
//        val fakeResponse: ListResponse = JsonProvider.objectFromJsonFileWithType(JSON)
        val fakeResponse = ListResponse()

        var results: ArrayList<ResultsItem> = ArrayList<ResultsItem>()
        val image_ids = ArrayList<String>()
        image_ids.add("9355183956e3445e89735d877b798689")
        val image_urls = ArrayList<String>()
        image_urls.add("https://demo-app-photos-45687895456123.s3.amazonaws.com/9355183956e3445e89735d877b798689?AWSAccessKeyId=ASIASV3YI6A4Q34I72X6&Signature=Eao6woZZZ4b7PQFMrnW3TXzhlaQ%3D&x-amz-security-token=IQoJb3JpZ2luX2VjECwaCXVzLWVhc3QtMSJHMEUCIDuRAmVL5qsFKJ3fDGzKOtI3zXyIqfPZyaj%2BgH7XKJEXAiEApGWv9sAaeTQnubfDX1CGYxamqPkZmMc3eIg%2Fgg3g%2FisqnQII1f%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARADGgwxODQzOTg5NjY4NDEiDKWeU1f7vZ5bK8bJcCrxAf2aRWpZiKrSRUj%2F5F5GYr0mN8yLCi%2BE5O6ajMNYcUbU92nT0G%2Fr2CRw2FPstNLdROsaSgBqFxyrkx9zXpzZGZ7RXqkVoiLfFL7twkcZZ4LqzV%2BBBTV2zCjWFtMAnFUYDLsBYdDbUt0GX%2FpfRDVaTfuea1vuX%2BGdTsTwl54Q%2FYl2lwtJ4P9e2PUy3tEdS3hLWUfDsPMUGhx15p3YZS72q8XO1VIl5q%2BrscHXBVTZM1ubxoJBFBGcQCkWleAal3MqMBZvtXkDOLqJR6IXlSt9AzabKOp7O1xdciUr15M7UeNFb253rx3lUgiomaJZyQA4%2B1wwz7SpjAY6mgGvue9vr%2FEXpfoAyfruHGeQHUtYSZXY2dpNDz%2FPXlt9Op5SCCSMymGmct8fZi6oVc1RDrKTrTrfnoXbxLx3tmELXMiRpW4oMUha7nsDt%2FmHSYslPa3RAiZw2sXLw%2BWhZtRuUn%2BkpV%2FAXjZSnJo%2FU72YFZP9VWkQ91%2BfHSP0PmgxllYGLb8wzX3NYWoZuIL2ejAyV3rLpBbqwOnw&Expires=1636461087")
        val image_urls_thumbnails = ArrayList<String>()
        image_urls_thumbnails.add("https://demo-app-photos-45687895456123.s3.amazonaws.com/9355183956e3445e89735d877b798689-thumbnail?AWSAccessKeyId=ASIASV3YI6A4Q34I72X6&Signature=SNtgmC63pZ%2FXZpzb4vYLUxLeGbY%3D&x-amz-security-token=IQoJb3JpZ2luX2VjECwaCXVzLWVhc3QtMSJHMEUCIDuRAmVL5qsFKJ3fDGzKOtI3zXyIqfPZyaj%2BgH7XKJEXAiEApGWv9sAaeTQnubfDX1CGYxamqPkZmMc3eIg%2Fgg3g%2FisqnQII1f%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARADGgwxODQzOTg5NjY4NDEiDKWeU1f7vZ5bK8bJcCrxAf2aRWpZiKrSRUj%2F5F5GYr0mN8yLCi%2BE5O6ajMNYcUbU92nT0G%2Fr2CRw2FPstNLdROsaSgBqFxyrkx9zXpzZGZ7RXqkVoiLfFL7twkcZZ4LqzV%2BBBTV2zCjWFtMAnFUYDLsBYdDbUt0GX%2FpfRDVaTfuea1vuX%2BGdTsTwl54Q%2FYl2lwtJ4P9e2PUy3tEdS3hLWUfDsPMUGhx15p3YZS72q8XO1VIl5q%2BrscHXBVTZM1ubxoJBFBGcQCkWleAal3MqMBZvtXkDOLqJR6IXlSt9AzabKOp7O1xdciUr15M7UeNFb253rx3lUgiomaJZyQA4%2B1wwz7SpjAY6mgGvue9vr%2FEXpfoAyfruHGeQHUtYSZXY2dpNDz%2FPXlt9Op5SCCSMymGmct8fZi6oVc1RDrKTrTrfnoXbxLx3tmELXMiRpW4oMUha7nsDt%2FmHSYslPa3RAiZw2sXLw%2BWhZtRuUn%2BkpV%2FAXjZSnJo%2FU72YFZP9VWkQ91%2BfHSP0PmgxllYGLb8wzX3NYWoZuIL2ejAyV3rLpBbqwOnw&Expires=1636461087")
        var data = ResultsItem(
            "4878bf592579410fba52941d00b62f94",
            "AED 5",
            "Notebook",
            "2019-02-24 04:04:17.566515",
            image_ids,image_urls,image_urls_thumbnails
        )
        results.add(data)
        fakeResponse.results = results


        if (wrongResponse) return fakeResponse.apply {
            this.results = null
        }

        return fakeResponse
    }

    companion object {
        private const val JSON = "/json/list.json"
    }


}