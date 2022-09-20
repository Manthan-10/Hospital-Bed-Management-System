import React from 'react';
import Enzyme,{ shallow, mount } from 'enzyme';
import LoginContainer from '../LoginContainer';
import Adapter from 'enzyme-adapter-react-16';
import * as reactRouterDom from "react-router-dom";
import renderer from 'react-test-renderer';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';

Enzyme.configure({ adapter: new Adapter() })

/**
 * Testing for UI of Login page
 * @author MN097789
 */
 

 const mockNavigate = jest.fn();

 jest.mock("react-router-dom", () => ({
 
   useSelector: jest.fn(),
 
   useNavigate: () => mockNavigate,
 
 }));
 const mockNavigateSpy = jest.spyOn(reactRouterDom, "useNavigate");

 
 describe('DefaultContainer UI Test Suite', () => {

  

  const initialState = { output: 10 };
    const mockStore = configureStore();
    let store;

  test('Checks if it renders correctly', () => {
    store = mockStore(initialState);
    const tree = renderer.create(<Provider store={store}><LoginContainer /></Provider>).toJSON();
    expect(tree).toMatchSnapshot();
  });


 
});