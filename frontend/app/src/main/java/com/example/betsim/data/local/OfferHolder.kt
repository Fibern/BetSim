package com.example.betsim.data.local

import com.example.betsim.data.remote.responses.Offer
import java.time.LocalDateTime

class OfferHolder {
    private var _offer: Offer = Offer(false, LocalDateTime.now(),-1, listOf(),"",0,-1,null, "")
    fun saveOffer(offer: Offer){
        _offer = offer
    }
    fun getOffer(): Offer{
        return _offer
    }
}