import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './upload-job.reducer';
import { IUploadJob } from 'app/shared/model/upload-job.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUploadJobDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UploadJobDetail = (props: IUploadJobDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { uploadJobEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jobsOpsToolApp.uploadJob.detail.title">UploadJob</Translate> [<b>{uploadJobEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="type">
              <Translate contentKey="jobsOpsToolApp.uploadJob.type">Type</Translate>
            </span>
          </dt>
          <dd>{uploadJobEntity.type}</dd>
          <dt>
            <span id="url">
              <Translate contentKey="jobsOpsToolApp.uploadJob.url">Url</Translate>
            </span>
          </dt>
          <dd>{uploadJobEntity.url}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="jobsOpsToolApp.uploadJob.status">Status</Translate>
            </span>
          </dt>
          <dd>{uploadJobEntity.status}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="jobsOpsToolApp.uploadJob.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{uploadJobEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="jobsOpsToolApp.uploadJob.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {uploadJobEntity.createdOn ? <TextFormat value={uploadJobEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="jobsOpsToolApp.uploadJob.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{uploadJobEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="jobsOpsToolApp.uploadJob.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {uploadJobEntity.updatedOn ? <TextFormat value={uploadJobEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/upload-job" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/upload-job/${uploadJobEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ uploadJob }: IRootState) => ({
  uploadJobEntity: uploadJob.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UploadJobDetail);
