package android.josephhowerton.travelingsalesman.data.foursquare

data class FoursquareVenue(
        val allowMenuUrlEdit: Boolean,
        val attributes: FoursquareVenueAttributes,
        val beenHere: FoursquareVenueBeenHere,
        val bestPhoto: FoursquareVenueBestPhoto,
        val canonicalUrl: String,
        val categories: List<FoursquareCategory>,
        val colors: FoursquareVenueColors,
        val contact: FoursquareVenueContact,
        val createdAt: Int,
        val defaultHours: FoursquareVenueDefaultHours,
        val dislike: Boolean,
        val hereNow: FoursquareVenueHereNow,
        val hierarchy: List<FoursquareVenueHierarchy>,
        val hours: FoursquareVenueHours,
        val id: String,
        val likes: FoursquareVenueLikes,
        val listed: FoursquareVenueListed,
        val location: FoursquareLocation,
        val name: String,
        val ok: Boolean,
        val pageUpdates: FoursquareVenuePageUpdates,
        val parent: FoursquareVenueParent,
        val photos: FoursquareVenuePhotos,
        val price: FoursquareVenuePrice,
        val rating: Double,
        val ratingColor: String,
        val ratingSignals: Int,
        val reasons: FoursquareVenueReasons,
        val shortUrl: String,
        val specials: FoursquareVenueSpecials,
        val stats: FoursquareVenueStats,
        val timeZone: String,
        val tips: FoursquareVenueTips,
        val verified: Boolean
)

data class FoursquareVenueAttributes(
    val groups: List<FoursquareVenueGroup>
)

data class FoursquareVenueGroup(
    val count: Int,
    val items: List<FoursquareVenueItem>,
    val name: String,
    val summary: String,
    val type: String
)

data class FoursquareVenueItem(
    val displayName: String,
    val displayValue: String,
    val priceTier: Int
)

data class FoursquareVenueBeenHere(
    val count: Int,
    val lastCheckinExpiredAt: Int,
    val marked: Boolean,
    val unconfirmedCount: Int
)

data class FoursquareVenueBestPhoto(
    val createdAt: Int,
    val height: Int,
    val id: String,
    val prefix: String,
    val source: FoursquareVenueSource,
    val suffix: String,
    val visibility: String,
    val width: Int
)

data class FoursquareVenueSource(
    val name: String,
    val url: String
)

data class FoursquareVenueColors(
    val algoVersion: Int,
    val highlightColor: FoursquareVenueHighlightColor,
    val highlightTextColor: FoursquareVenueHighlightTextColor
)

data class FoursquareVenueHighlightColor(
    val photoId: String,
    val value: Int
)

data class FoursquareVenueHighlightTextColor(
    val photoId: String,
    val value: Int
)

data class FoursquareVenueContact(
    val formattedPhone: String,
    val phone: String
)

data class FoursquareVenueHours(
    val isLocalHoliday: Boolean,
    val isOpen: Boolean,
    val richStatus: FoursquareVenueRichStatus,
    val status: String,
    val timeframes: List<FoursquareVenueTimeframe>
)

data class FoursquareVenueDefaultHours(
    val isLocalHoliday: Boolean,
    val isOpen: Boolean,
    val richStatus: FoursquareVenueRichStatus,
    val status: String,
    val timeframes: List<FoursquareVenueTimeframe>
)

data class FoursquareVenueRichStatus(
    val text: String
)

data class FoursquareVenueTimeframe(
    val days: String,
    val includesToday: Boolean,
    val open: List<FoursquareVenueOpen>,
)

data class FoursquareVenueHereNow(
    val count: Int,
    val groups: List<Any>,
    val summary: String
)

data class FoursquareVenueHierarchy(
    val canonicalUrl: String,
    val id: String,
    val lang: String,
    val name: String
)

data class FoursquareVenueLikes(
    val count: Int,
    val groups: List<FoursquareVenueLikesGroup>,
    val summary: String
)

data class FoursquareVenueLikesGroup(
        val count: Int,
        val items: List<FoursquareVenueLikesItem>,
        val type: String
)

data class FoursquareVenueLikesItem(
        val countryCode: String,
        val firstName: String,
        val lastName: String
)

data class FoursquareVenueListed(
        val count: Int,
        val groups: List<FoursquareVenueListedGroup>
)

data class FoursquareVenueListedGroup(
        val count: Int,
        val items: List<FoursquareVenueListedItem>,
        val name: String,
        val type: String
)

data class FoursquareVenueListedItem(
        val canonicalUrl: String,
        val collaborative: Boolean,
        val createdAt: Int,
        val description: String,
        val editable: Boolean,
        val followers: FoursquareVenueFollowers,
        val id: String,
        val listItems: FoursquareVenueListItems,
        val name: String,
        val photo: FoursquareVenuePhoto,
        val `public`: Boolean,
        val type: String,
        val updatedAt: Int,
        val url: String,
)

data class FoursquareVenueFollowers(
        val count: Int
)

data class FoursquareVenueListItems(
        val count: Int,
        val items: List<FoursquareVenueListItemsItem>
)

data class FoursquareVenueListItemsItem(
        val createdAt: Int,
        val id: String
)

data class FoursquareVenuePhoto(
        val createdAt: Int,
        val height: Int,
        val id: String,
        val prefix: String,
        val suffix: String,
        val visibility: String,
        val width: Int
)

data class FoursquareVenuePageUpdates(
        val count: Int,
)

data class FoursquareVenueParent(
        val categories: List<FoursquareCategory>,
        val id: String,
        val location: FoursquareLocation,
        val name: String
)

data class FoursquareVenuePhotos(
        val count: Int,
        val groups: List<FoursquareVenuePhotosGroup>
)

data class FoursquareVenuePhotosGroup(
    val count: Int,
    val items: List<FoursquareVenuePhotosGroupItem>,
    val name: String,
    val type: String
)

data class FoursquareVenuePhotosGroupItem(
    val createdAt: Int,
    val height: Int,
    val id: String,
    val prefix: String,
    val source: FoursquareVenueSource,
    val suffix: String,
    val visibility: String,
    val width: Int
)

data class FoursquareVenuePrice(
    val currency: String,
    val message: String,
    val tier: Int
)

data class FoursquareVenueReasons(
    val count: Int
)

data class FoursquareVenueSpecials(
    val count: Int
)

data class FoursquareVenueStats(
    val tipCount: Int
)

data class FoursquareVenueTips(
    val count: Int,
    val groups: List<FoursquareVenueTipsGroup>
)

data class FoursquareVenueTipsGroup(
        val count: Int,
        val items: List<FoursquareVenueTipsGroupItem>,
        val name: String,
        val type: String
)

data class FoursquareVenueTipsGroupItem(
        val agreeCount: Int,
        val authorInteractionType: String,
        val canonicalUrl: String,
        val createdAt: Int,
        val disagreeCount: Int,
        val id: String,
        val lang: String,
        val likes: FoursquareVenueLikes,
        val logView: Boolean,
        val text: String,
        val type: String,
)

data class FoursquareVenueOpen(
    val renderedTime: String
)

