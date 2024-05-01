package ctu.cit.se.post_service.mappers;

public interface IMapper <S,D>{
    D convert(S source);
}
