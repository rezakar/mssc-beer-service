package com.msscbeerservice.events;

import com.msscbeerservice.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -5781515597148163111L;

    private BeerDto beerDto;

}
