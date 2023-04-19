package com.seminolestate.mypokedex

class SearchItem constructor() {
    var id: Int = 0
    var searchText: String = ""

    constructor (newSearchText: String) : this(){
        this.searchText = newSearchText
    }
}