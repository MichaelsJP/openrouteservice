package heigit.ors.api.responses.routing.JSONRouteResponseObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vividsolutions.jts.geom.Coordinate;
import heigit.ors.api.responses.routing.GeometryResponse;
import heigit.ors.util.PolylineEncoder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("EncodedPolyLineGeometryResponse")
public class EncodedPolylineGeometryResponse extends GeometryResponse {
    public EncodedPolylineGeometryResponse(Coordinate[]coordinates, boolean includeElevation) {
        super(coordinates, includeElevation);
    }

    @ApiModelProperty(dataType = "String")
    @JsonProperty("geometry")
    @Override
    public Object getGeometry() {
        StringBuffer strBuffer = new StringBuffer();
        return PolylineEncoder.encode(coordinates, includeElevation, strBuffer);
    }
}
