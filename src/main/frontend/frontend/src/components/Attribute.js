import React from 'react';
import styled from '@emotion/styled';
import TextInput from './inputs/TextInput';
import SelectInput from './inputs/SelectInput';
import { getNewIdGenerator } from '../utils';

const attributeKeysGen = getNewIdGenerator();

const Container = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  margin: 3% auto;
`;

const Attribute = ({ numField }) => {
  return (
    <Container>
      <TextInput
        labelName="Attribute name"
        nameForm={`Attribute name ${numField}`}
        labeId={`Attribute name ${attributeKeysGen()}`}
      />
      <SelectInput
        type="attribute"
        labelName="Attr type"
        nameForm={`Attr type ${numField}`}
        labeId={`Attr type ${attributeKeysGen()}`}
      />
      <div style={{ marginLeft: '3%' }}>
        Required
        <input
          style={{
            display: 'block',
            margin: '20% auto',
            transform: 'scale(1.5)',
          }}
          type="checkbox"
          name={`Checkbox ${numField}`}
        />
      </div>
    </Container>
  );
};

export default Attribute;
