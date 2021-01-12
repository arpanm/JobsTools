import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUploadJob } from 'app/shared/model/upload-job.model';
import { getEntities as getUploadJobs } from 'app/entities/upload-job/upload-job.reducer';
import { getEntity, updateEntity, createEntity, reset } from './q-cash-upload-row.reducer';
import { IQCashUploadRow } from 'app/shared/model/q-cash-upload-row.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IQCashUploadRowUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const QCashUploadRowUpdate = (props: IQCashUploadRowUpdateProps) => {
  const [jobIdId, setJobIdId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { qCashUploadRowEntity, uploadJobs, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/q-cash-upload-row');
  };

  useEffect(() => {
    if (!isNew) {
      props.getEntity(props.match.params.id);
    }

    props.getUploadJobs();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...qCashUploadRowEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="jobsOpsToolApp.qCashUploadRow.home.createOrEditLabel">
            <Translate contentKey="jobsOpsToolApp.qCashUploadRow.home.createOrEditLabel">Create or edit a QCashUploadRow</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : qCashUploadRowEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="q-cash-upload-row-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="q-cash-upload-row-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="babelUserIdLabel" for="q-cash-upload-row-babelUserId">
                  <Translate contentKey="jobsOpsToolApp.qCashUploadRow.babelUserId">Babel User Id</Translate>
                </Label>
                <AvField id="q-cash-upload-row-babelUserId" type="string" className="form-control" name="babelUserId" />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="q-cash-upload-row-email">
                  <Translate contentKey="jobsOpsToolApp.qCashUploadRow.email">Email</Translate>
                </Label>
                <AvField id="q-cash-upload-row-email" type="text" name="email" />
              </AvGroup>
              <AvGroup>
                <Label id="phoneLabel" for="q-cash-upload-row-phone">
                  <Translate contentKey="jobsOpsToolApp.qCashUploadRow.phone">Phone</Translate>
                </Label>
                <AvField id="q-cash-upload-row-phone" type="string" className="form-control" name="phone" />
              </AvGroup>
              <AvGroup>
                <Label id="amountLabel" for="q-cash-upload-row-amount">
                  <Translate contentKey="jobsOpsToolApp.qCashUploadRow.amount">Amount</Translate>
                </Label>
                <AvField id="q-cash-upload-row-amount" type="string" className="form-control" name="amount" />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="q-cash-upload-row-status">
                  <Translate contentKey="jobsOpsToolApp.qCashUploadRow.status">Status</Translate>
                </Label>
                <AvInput
                  id="q-cash-upload-row-status"
                  type="select"
                  className="form-control"
                  name="status"
                  value={(!isNew && qCashUploadRowEntity.status) || 'INIT'}
                >
                  <option value="INIT">{translate('jobsOpsToolApp.QCashUploadRowStatus.INIT')}</option>
                  <option value="PUSHED">{translate('jobsOpsToolApp.QCashUploadRowStatus.PUSHED')}</option>
                  <option value="PROCESSING">{translate('jobsOpsToolApp.QCashUploadRowStatus.PROCESSING')}</option>
                  <option value="DONE">{translate('jobsOpsToolApp.QCashUploadRowStatus.DONE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="q-cash-upload-row-createdBy">
                  <Translate contentKey="jobsOpsToolApp.qCashUploadRow.createdBy">Created By</Translate>
                </Label>
                <AvField id="q-cash-upload-row-createdBy" type="text" name="createdBy" />
              </AvGroup>
              <AvGroup>
                <Label id="createdOnLabel" for="q-cash-upload-row-createdOn">
                  <Translate contentKey="jobsOpsToolApp.qCashUploadRow.createdOn">Created On</Translate>
                </Label>
                <AvField id="q-cash-upload-row-createdOn" type="date" className="form-control" name="createdOn" />
              </AvGroup>
              <AvGroup>
                <Label id="updatedByLabel" for="q-cash-upload-row-updatedBy">
                  <Translate contentKey="jobsOpsToolApp.qCashUploadRow.updatedBy">Updated By</Translate>
                </Label>
                <AvField id="q-cash-upload-row-updatedBy" type="text" name="updatedBy" />
              </AvGroup>
              <AvGroup>
                <Label id="updatedOnLabel" for="q-cash-upload-row-updatedOn">
                  <Translate contentKey="jobsOpsToolApp.qCashUploadRow.updatedOn">Updated On</Translate>
                </Label>
                <AvField id="q-cash-upload-row-updatedOn" type="date" className="form-control" name="updatedOn" />
              </AvGroup>
              <AvGroup>
                <Label for="q-cash-upload-row-jobId">
                  <Translate contentKey="jobsOpsToolApp.qCashUploadRow.jobId">Job Id</Translate>
                </Label>
                <AvInput id="q-cash-upload-row-jobId" type="select" className="form-control" name="jobIdId">
                  <option value="" key="0" />
                  {uploadJobs
                    ? uploadJobs.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/q-cash-upload-row" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  uploadJobs: storeState.uploadJob.entities,
  qCashUploadRowEntity: storeState.qCashUploadRow.entity,
  loading: storeState.qCashUploadRow.loading,
  updating: storeState.qCashUploadRow.updating,
  updateSuccess: storeState.qCashUploadRow.updateSuccess,
});

const mapDispatchToProps = {
  getUploadJobs,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(QCashUploadRowUpdate);
