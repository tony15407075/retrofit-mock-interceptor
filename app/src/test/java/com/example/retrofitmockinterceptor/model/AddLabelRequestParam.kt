package com.example.retrofitmockinterceptor.model

class AddLabelRequestParam(var group_id: Int, title: String) {
    var label: LabelParam

    init {
        this.label = LabelParam(group_id, title)
    }

    inner class LabelParam(var group_id: Int, var title: String) {
        var tag_name: String

        init {
            this.tag_name = title
        }
    }
}
