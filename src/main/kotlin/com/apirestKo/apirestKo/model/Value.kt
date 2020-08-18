package com.apirestKo.apirestKo.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Value(var id:Long? = null, var quote:String? = null)