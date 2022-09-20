import React from 'react';
import Enzyme,{ shallow, mount } from 'enzyme';
import Sidebar from '../components/Sidebar';
import Adapter from 'enzyme-adapter-react-16';
import * as reactRouterDom from "react-router-dom";
import renderer from 'react-test-renderer';

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

 
 describe('Sidebar UI Test Suite', () => {

    let wrapper;
  beforeEach(() => {
    wrapper = shallow(<Sidebar />);
  });

  test('Checks if it renders correctly', () => {
    const tree = renderer.create(<Sidebar/>).toJSON();
    expect(tree).toMatchSnapshot();
  });


  test('should check for divs', () => {
    wrapper = shallow(<Sidebar/>);
    expect(wrapper.length).toEqual(1);
  

    });
});