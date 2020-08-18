package com.apirestKo.apirestKo.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Quote (var type:String = "", var value:Value? = null)