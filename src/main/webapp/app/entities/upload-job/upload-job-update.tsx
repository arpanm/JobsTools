import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './upload-job.reducer';
import { IUploadJob } from 'app/shared/model/upload-job.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IUploadJobUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UploadJobUpdate = (props: IUploadJobUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { uploadJobEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/upload-job');
  };

  useEffect(() => {
    if (!isNew) {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...uploadJobEntity,
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
          <h2 id="jobsOpsToolApp.uploadJob.home.createOrEditLabel">
            <Translate contentKey="jobsOpsToolApp.uploadJob.home.createOrEditLabel">Create or edit a UploadJob</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : uploadJobEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="upload-job-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="upload-job-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="typeLabel" for="upload-job-type">
                  <Translate contentKey="jobsOpsToolApp.uploadJob.type">Type</Translate>
                </Label>
                <AvInput
                  id="upload-job-type"
                  type="select"
                  className="form-control"
                  name="type"
                  value={(!isNew && uploadJobEntity.type) || 'QCash'}
                >
                  <option value="QCash">{translate('jobsOpsToolApp.UploadType.QCash')}</option>
                  <option value="Other">{translate('jobsOpsToolApp.UploadType.Other')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="urlLabel" for="upload-job-url">
                  <Translate contentKey="jobsOpsToolApp.uploadJob.url">Url</Translate>
                </Label>
                <AvField id="upload-job-url" type="text" name="url" />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="upload-job-status">
                  <Translate contentKey="jobsOpsToolApp.uploadJob.status">Status</Translate>
                </Label>
                <AvInput
                  id="upload-job-status"
                  type="select"
                  className="form-control"
                  name="status"
                  value={(!isNew && uploadJobEntity.status) || 'INIT'}
                >
                  <option value="INIT">{translate('jobsOpsToolApp.UploadStatus.INIT')}</option>
                  <option value="PROCESSING">{translate('jobsOpsToolApp.UploadStatus.PROCESSING')}</option>
                  <option value="PUSHED">{translate('jobsOpsToolApp.UploadStatus.PUSHED')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="upload-job-createdBy">
                  <Translate contentKey="jobsOpsToolApp.uploadJob.createdBy">Created By</Translate>
                </Label>
                <AvField id="upload-job-createdBy" type="text" name="createdBy" />
              </AvGroup>
              <AvGroup>
                <Label id="createdOnLabel" for="upload-job-createdOn">
                  <Translate contentKey="jobsOpsToolApp.uploadJob.createdOn">Created On</Translate>
                </Label>
                <AvField id="upload-job-createdOn" type="date" className="form-control" name="createdOn" />
              </AvGroup>
              <AvGroup>
                <Label id="updatedByLabel" for="upload-job-updatedBy">
                  <Translate contentKey="jobsOpsToolApp.uploadJob.updatedBy">Updated By</Translate>
                </Label>
                <AvField id="upload-job-updatedBy" type="text" name="updatedBy" />
              </AvGroup>
              <AvGroup>
                <Label id="updatedOnLabel" for="upload-job-updatedOn">
                  <Translate contentKey="jobsOpsToolApp.uploadJob.updatedOn">Updated On</Translate>
                </Label>
                <AvField id="upload-job-updatedOn" type="date" className="form-control" name="updatedOn" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/upload-job" replace color="info">
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
  uploadJobEntity: storeState.uploadJob.entity,
  loading: storeState.uploadJob.loading,
  updating: storeState.uploadJob.updating,
  updateSuccess: storeState.uploadJob.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UploadJobUpdate);
