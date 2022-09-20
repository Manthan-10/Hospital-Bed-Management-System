import React from 'react';
import Enzyme,{ shallow, mount } from 'enzyme';
import Floor from '../components/Floor';
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
 const initialState = { output: 10 };
    const mockStore = configureStore();
    let store;

 
 describe('Floor UI Test Suite', () => {

    let wrapper;
  beforeEach(() => {
    wrapper = shallow(<Floor />);
  });

  test('Checks if it renders correctly', () => {
    store = mockStore(initialState);
    const tree = renderer.create(<Provider store={store}><Floor /></Provider>).toJSON();
    expect(tree).toMatchSnapshot();
  });


  test('should check for divs', () => {
    wrapper = shallow(<Floor />);
    expect(wrapper.length).toEqual(1);
  

    });
});