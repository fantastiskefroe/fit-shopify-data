package dk.fantastiskefroe.it.shopify_data.dto.input.order

import dk.fantastiskefroe.it.shopify_data.entity.order.RestockType

enum class RestockTypeInput {
    NO_RESTOCK, CANCEL, RETURN
}

fun RestockTypeInput?.toInternal(): RestockType {
    return when (this) {
        RestockTypeInput.NO_RESTOCK -> RestockType.NO_RESTOCK
        RestockTypeInput.CANCEL -> RestockType.CANCEL
        RestockTypeInput.RETURN -> RestockType.RETURN
        null -> RestockType.NULL
    }
}