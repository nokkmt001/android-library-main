package com.lediya.dagger2.data


/**
 * Store the response data in the model class*/
data class CountryListResponse(
    val rows: List<Row>,
    val title: String
)

data class Row(
    val description: String?,
    val imageHref: String?,
    val title: String?
)