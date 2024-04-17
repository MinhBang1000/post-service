package ctu.cit.se.generalinformation.daos.mappers;

public interface IMapper <S,D>{
    D convert(S source);
}
