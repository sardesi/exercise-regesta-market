package com.regesta.exercise.regestamarket.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the composite key for the table ProductSupplier.
 * @author ars
 *
 */
@Embeddable
@Getter
@Setter
public class ProductSupplierPK implements Serializable {

	private static final long serialVersionUID = -3138479834718373545L;

	@Column(name = "PRODUCT_ID", unique = true, nullable = false)
	private Integer productId;
	
	@Column(name = "SUPPLIER_ID", unique = true, nullable = false)
	private Integer supplierId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((supplierId == null) ? 0 : supplierId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductSupplierPK other = (ProductSupplierPK) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (supplierId == null) {
			if (other.supplierId != null)
				return false;
		} else if (!supplierId.equals(other.supplierId))
			return false;
		return true;
	}

}
