package co.com.poli.tienda.repository.implement;

import co.com.poli.tienda.domain.common.DetalleFactura;
import co.com.poli.tienda.domain.common.Factura;
import co.com.poli.tienda.repository.entity.DetalleFacturaEntity;
import co.com.poli.tienda.repository.entity.FacturaEntity;
import co.com.poli.tienda.repository.port.FacturaRepositoryPort;
import co.com.poli.tienda.repository.repository.BdDetalleFacturaRepository;
import co.com.poli.tienda.repository.repository.BdFacturaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FacturaReposiotry implements FacturaRepositoryPort {

    private final BdFacturaRepository facturaRepository;
    private final BdDetalleFacturaRepository detalleFacturaRepository;
    private final ModelMapper modelMapper;

    public FacturaReposiotry(BdFacturaRepository facturaRepository,
                             BdDetalleFacturaRepository detalleFacturaRepository,
                             ModelMapper modelMapper) {
        this.facturaRepository = facturaRepository;
        this.detalleFacturaRepository = detalleFacturaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Factura guardarFactura(Factura factura) {
        FacturaEntity entity = modelMapper.map(factura, FacturaEntity.class);
        return modelMapper.map(facturaRepository.save(entity), Factura.class);
    }

    @Override
    public DetalleFactura guardarDetalleFactura(DetalleFactura detalleFactura) {
        DetalleFacturaEntity entity = modelMapper.map(detalleFactura, DetalleFacturaEntity.class);
        return modelMapper.map(detalleFacturaRepository.save(entity), DetalleFactura.class);
    }
}
