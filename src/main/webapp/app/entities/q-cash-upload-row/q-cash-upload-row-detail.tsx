import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './q-cash-upload-row.reducer';
import { IQCashUploadRow } from 'app/shared/model/q-cash-upload-row.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IQCashUploadRowDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const QCashUploadRowDetail = (props: IQCashUploadRowDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { qCashUploadRowEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jobsOpsToolApp.qCashUploadRow.detail.title">QCashUploadRow</Translate> [<b>{qCashUploadRowEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="babelUserId">
              <Translate contentKey="jobsOpsToolApp.qCashUploadRow.babelUserId">Babel User Id</Translate>
            </span>
          </dt>
          <dd>{qCashUploadRowEntity.babelUserId}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="jobsOpsToolApp.qCashUploadRow.email">Email</Translate>
            </span>
          </dt>
          <dd>{qCashUploadRowEntity.email}</dd>
          <dt>
            <span id="phone">
              <Translate contentKey="jobsOpsToolApp.qCashUploadRow.phone">Phone</Translate>
            </span>
          </dt>
          <dd>{qCashUploadRowEntity.phone}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="jobsOpsToolApp.qCashUploadRow.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{qCashUploadRowEntity.amount}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="jobsOpsToolApp.qCashUploadRow.status">Status</Translate>
            </span>
          </dt>
          <dd>{qCashUploadRowEntity.status}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="jobsOpsToolApp.qCashUploadRow.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{qCashUploadRowEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="jobsOpsToolApp.qCashUploadRow.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {qCashUploadRowEntity.createdOn ? (
              <TextFormat value={qCashUploadRowEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="jobsOpsToolApp.qCashUploadRow.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{qCashUploadRowEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="jobsOpsToolApp.qCashUploadRow.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {qCashUploadRowEntity.updatedOn ? (
              <TextFormat value={qCashUploadRowEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="jobsOpsToolApp.qCashUploadRow.jobId">Job Id</Translate>
          </dt>
          <dd>{qCashUploadRowEntity.jobIdId ? qCashUploadRowEntity.jobIdId : ''}</dd>
        </dl>
        <Button tag={Link} to="/q-cash-upload-row" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/q-cash-upload-row/${qCashUploadRowEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ qCashUploadRow }: IRootState) => ({
  qCashUploadRowEntity: qCashUploadRow.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(QCashUploadRowDetail);
