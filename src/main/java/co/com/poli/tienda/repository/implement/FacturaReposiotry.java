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

import java.util.List;
import java.util.Optional;

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
    public Factura consultarFacturaPorId(String idFactura) {
        Optional<FacturaEntity> entity = facturaRepository.findById(idFactura);
        return (entity.isPresent()) ? modelMapper.map(entity, Factura.class) : null;
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

    @Override
    public List<Factura> consultarFacturasCliente(Integer idPersona) {
        List<FacturaEntity> facturas = facturaRepository.findByIdPersona(idPersona);
        return facturas.stream().map(f -> modelMapper.map(f, Factura.class)).toList();
    }

    @Override
    public List<DetalleFactura> consultarDetallesFactura(String idFactura) {
        List<DetalleFacturaEntity> detalles = detalleFacturaRepository.findByIdFactura(idFactura);
        return detalles.stream().map(d -> modelMapper.map(d, DetalleFactura.class)).toList();
    }
}
