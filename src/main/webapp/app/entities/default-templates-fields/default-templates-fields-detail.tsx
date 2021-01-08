import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './default-templates-fields.reducer';
import { IDefaultTemplatesFields } from 'app/shared/model/default-templates-fields.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDefaultTemplatesFieldsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DefaultTemplatesFieldsDetail = (props: IDefaultTemplatesFieldsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { defaultTemplatesFieldsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jobsOpsToolApp.defaultTemplatesFields.detail.title">DefaultTemplatesFields</Translate> [
          <b>{defaultTemplatesFieldsEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="jobsOpsToolApp.defaultTemplatesFields.name">Name</Translate>
            </span>
          </dt>
          <dd>{defaultTemplatesFieldsEntity.name}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="jobsOpsToolApp.defaultTemplatesFields.type">Type</Translate>
            </span>
          </dt>
          <dd>{defaultTemplatesFieldsEntity.type}</dd>
          <dt>
            <span id="displayName">
              <Translate contentKey="jobsOpsToolApp.defaultTemplatesFields.displayName">Display Name</Translate>
            </span>
          </dt>
          <dd>{defaultTemplatesFieldsEntity.displayName}</dd>
        </dl>
        <Button tag={Link} to="/default-templates-fields" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/default-templates-fields/${defaultTemplatesFieldsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ defaultTemplatesFields }: IRootState) => ({
  defaultTemplatesFieldsEntity: defaultTemplatesFields.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DefaultTemplatesFieldsDetail);
