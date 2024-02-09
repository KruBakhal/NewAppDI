package com.writi.extension.custom.tab

import androidx.databinding.ObservableField


class TabViewModel {

    var tabModel: TabModel? = null
    val tabId = ObservableField<Int>()
    val tabIcon = ObservableField<String>()
    val tabName = ObservableField<String>()
    val tabBadge = ObservableField<String>()
    val selected = ObservableField<Boolean>()
}