import React, { useState, useEffect } from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './q-cash-upload-row.reducer';
import { IQCashUploadRow } from 'app/shared/model/q-cash-upload-row.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IQCashUploadRowProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const QCashUploadRow = (props: IQCashUploadRowProps) => {
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE), props.location.search)
  );
  const [sorting, setSorting] = useState(false);

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
  };

  const resetAll = () => {
    props.reset();
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    props.getEntities();
  };

  useEffect(() => {
    resetAll();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      resetAll();
    }
  }, [props.updateSuccess]);

  useEffect(() => {
    getAllEntities();
  }, [paginationState.activePage]);

  const handleLoadMore = () => {
    if ((window as any).pageYOffset > 0) {
      setPaginationState({
        ...paginationState,
        activePage: paginationState.activePage + 1,
      });
    }
  };

  useEffect(() => {
    if (sorting) {
      getAllEntities();
      setSorting(false);
    }
  }, [sorting]);

  const sort = p => () => {
    props.reset();
    setPaginationState({
      ...paginationState,
      activePage: 1,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p,
    });
    setSorting(true);
  };

  const { qCashUploadRowList, match, loading } = props;
  return (
    <div>
      <h2 id="q-cash-upload-row-heading">
        <Translate contentKey="jobsOpsToolApp.qCashUploadRow.home.title">Q Cash Upload Rows</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="jobsOpsToolApp.qCashUploadRow.home.createLabel">Create new Q Cash Upload Row</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          pageStart={paginationState.activePage}
          loadMore={handleLoadMore}
          hasMore={paginationState.activePage - 1 < props.links.next}
          loader={<div className="loader">Loading ...</div>}
          threshold={0}
          initialLoad={false}
        >
          {qCashUploadRowList && qCashUploadRowList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('babelUserId')}>
                    <Translate contentKey="jobsOpsToolApp.qCashUploadRow.babelUserId">Babel User Id</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('email')}>
                    <Translate contentKey="jobsOpsToolApp.qCashUploadRow.email">Email</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('phone')}>
                    <Translate contentKey="jobsOpsToolApp.qCashUploadRow.phone">Phone</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('amount')}>
                    <Translate contentKey="jobsOpsToolApp.qCashUploadRow.amount">Amount</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('status')}>
                    <Translate contentKey="jobsOpsToolApp.qCashUploadRow.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdBy')}>
                    <Translate contentKey="jobsOpsToolApp.qCashUploadRow.createdBy">Created By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdOn')}>
                    <Translate contentKey="jobsOpsToolApp.qCashUploadRow.createdOn">Created On</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedBy')}>
                    <Translate contentKey="jobsOpsToolApp.qCashUploadRow.updatedBy">Updated By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedOn')}>
                    <Translate contentKey="jobsOpsToolApp.qCashUploadRow.updatedOn">Updated On</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="jobsOpsToolApp.qCashUploadRow.jobId">Job Id</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {qCashUploadRowList.map((qCashUploadRow, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${qCashUploadRow.id}`} color="link" size="sm">
                        {qCashUploadRow.id}
                      </Button>
                    </td>
                    <td>{qCashUploadRow.babelUserId}</td>
                    <td>{qCashUploadRow.email}</td>
                    <td>{qCashUploadRow.phone}</td>
                    <td>{qCashUploadRow.amount}</td>
                    <td>
                      <Translate contentKey={`jobsOpsToolApp.QCashUploadRowStatus.${qCashUploadRow.status}`} />
                    </td>
                    <td>{qCashUploadRow.createdBy}</td>
                    <td>
                      {qCashUploadRow.createdOn ? (
                        <TextFormat type="date" value={qCashUploadRow.createdOn} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>{qCashUploadRow.updatedBy}</td>
                    <td>
                      {qCashUploadRow.updatedOn ? (
                        <TextFormat type="date" value={qCashUploadRow.updatedOn} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>
                      {qCashUploadRow.jobIdId ? <Link to={`upload-job/${qCashUploadRow.jobIdId}`}>{qCashUploadRow.jobIdId}</Link> : ''}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${qCashUploadRow.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${qCashUploadRow.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${qCashUploadRow.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.delete">Delete</Translate>
                          </span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            !loading && (
              <div className="alert alert-warning">
                <Translate contentKey="jobsOpsToolApp.qCashUploadRow.home.notFound">No Q Cash Upload Rows found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

const mapStateToProps = ({ qCashUploadRow }: IRootState) => ({
  qCashUploadRowList: qCashUploadRow.entities,
  loading: qCashUploadRow.loading,
  totalItems: qCashUploadRow.totalItems,
  links: qCashUploadRow.links,
  entity: qCashUploadRow.entity,
  updateSuccess: qCashUploadRow.updateSuccess,
});

const mapDispatchToProps = {
  getEntities,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(QCashUploadRow);
