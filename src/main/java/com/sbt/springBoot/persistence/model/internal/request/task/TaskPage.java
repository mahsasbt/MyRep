package com.sbt.springBoot.persistence.model.internal.request.task;

import java.io.Serializable;

import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskPage implements Serializable {

	private static final long serialVersionUID = -785922643908227215L;

	private Integer pageNumber;
	private Integer size;
	private String sort;
	private Sort.Direction sortDirection;
	private String sortColumn;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Sort.Direction getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(Sort.Direction sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	@Override
	public String toString() {
		return "TaskPage [pageNumber=" + pageNumber + ", size=" + size + ", sort=" + sort + ", sortDirection="
				+ sortDirection + ", sortColumn=" + sortColumn + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pageNumber == null) ? 0 : pageNumber.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((sort == null) ? 0 : sort.hashCode());
		result = prime * result + ((sortColumn == null) ? 0 : sortColumn.hashCode());
		result = prime * result + ((sortDirection == null) ? 0 : sortDirection.hashCode());
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
		TaskPage other = (TaskPage) obj;
		if (pageNumber == null) {
			if (other.pageNumber != null)
				return false;
		} else if (!pageNumber.equals(other.pageNumber))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		if (sortColumn == null) {
			if (other.sortColumn != null)
				return false;
		} else if (!sortColumn.equals(other.sortColumn))
			return false;
		if (sortDirection != other.sortDirection)
			return false;
		return true;
	}

	public boolean equalsBasic(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskPage other = (TaskPage) obj;
		if (pageNumber == null) {
			if (other.pageNumber != null)
				return false;
		} else if (!pageNumber.equals(other.pageNumber))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}

	public boolean equalsAdvance(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskPage other = (TaskPage) obj;
		if (pageNumber == null) {
			if (other.pageNumber != null)
				return false;
		} else if (!pageNumber.equals(other.pageNumber))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		if (sortColumn == null) {
			if (other.sortColumn != null)
				return false;
		} else if (!sortColumn.equals(other.sortColumn))
			return false;
		return true;
	}	
}