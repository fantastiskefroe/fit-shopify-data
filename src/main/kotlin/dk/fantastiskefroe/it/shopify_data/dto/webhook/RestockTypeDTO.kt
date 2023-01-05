package dk.fantastiskefroe.it.shopify_data.dto.webhook

import dk.fantastiskefroe.it.shopify_data.entity.RestockType

enum class RestockTypeDTO {
    NO_RESTOCK, CANCEL, RETURN
}

fun RestockTypeDTO?.toInternal(): RestockType {
    return when (this) {
        RestockTypeDTO.NO_RESTOCK -> RestockType.NO_RESTOCK
        RestockTypeDTO.CANCEL -> RestockType.CANCEL
        RestockTypeDTO.RETURN -> RestockType.RETURN
        null -> RestockType.NULL
    }
}