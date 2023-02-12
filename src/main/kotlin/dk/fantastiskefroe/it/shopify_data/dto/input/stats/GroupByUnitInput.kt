package dk.fantastiskefroe.it.shopify_data.dto.input.stats

import dk.fantastiskefroe.it.shopify_data.entity.stats.GroupByUnit

enum class GroupByUnitInput {
    HOUR, DAY, MONTH, YEAR
}

fun GroupByUnitInput.toInternal(): GroupByUnit {
    return when (this) {
        GroupByUnitInput.HOUR -> GroupByUnit.HOURLY
        GroupByUnitInput.DAY -> GroupByUnit.DAILY
        GroupByUnitInput.MONTH -> GroupByUnit.MONTHLY
        GroupByUnitInput.YEAR -> GroupByUnit.YEARLY
    }
}
