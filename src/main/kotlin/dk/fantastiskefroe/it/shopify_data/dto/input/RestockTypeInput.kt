package dk.fantastiskefroe.it.shopify_data.dto.input

import dk.fantastiskefroe.it.shopify_data.entity.RestockType

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