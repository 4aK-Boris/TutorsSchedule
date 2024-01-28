package dmitriy.losev.core.serializers

import android.net.Uri
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object UriSerializer : KSerializer<Uri> {

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor =
        SerialDescriptor(serialName = "Uri", ByteArraySerializer().descriptor)

    override fun deserialize(decoder: Decoder): Uri {
        return Uri.parse(
            ByteArraySerializer().deserialize(decoder).toString(charset = Charsets.UTF_8)
        )
    }

    override fun serialize(encoder: Encoder, value: Uri) {
        ByteArraySerializer().serialize(
            encoder,
            value.toString().toByteArray(charset = Charsets.UTF_8)
        )
    }
}