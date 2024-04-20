package ctu.cit.se.post_service.daos.mappers;

public interface IMapper <S,D>{
    D convert(S source);
}
