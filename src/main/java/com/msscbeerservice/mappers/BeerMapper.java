package com.msscbeerservice.mappers;

import com.msscbeerservice.domain.Beer;
import com.msscbeerservice.model.BeerDto;
import com.msscbeerservice.model.BeerStyleEnum;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    default BeerStyleEnum toBeerStyleEnum(String beerStyle) {
        if ( beerStyle == null) {
            return null;
        }
        return switch(beerStyle) {
            case "IPA" -> BeerStyleEnum.IPA;
            case "PALE_ALE" -> BeerStyleEnum.PALE_ALE;
            default -> null;
        };
    }

    Beer beerDtoToBeer(BeerDto beerDto);

    default String toBeerStyle(BeerStyleEnum beerStyle) {
        if ( beerStyle == null) {
            return null;
        }
        return switch(beerStyle) {
            case IPA -> "IPA";
            case PALE_ALE -> "PALE_ALE";
            default -> null;
        };
    }
}
