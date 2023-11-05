'use client';

import React, { useEffect, useRef } from 'react';
import * as THREE from 'three';
import { GLTFLoader } from 'three/addons/loaders/GLTFLoader.js';

const MyThreeComponent = () => {
  const containerRef = useRef<HTMLDivElement>(null);
  const rendererRef = useRef<THREE.WebGLRenderer | null>(null);
  const scene = new THREE.Scene();
  const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);

  useEffect(() => {
    // Set up the renderer
    const renderer = new THREE.WebGLRenderer();
    renderer.setSize(window.innerWidth, window.innerHeight);
    containerRef.current?.appendChild(renderer.domElement);
    rendererRef.current = renderer; // Store the renderer reference

    // Set the background color
    scene.background = new THREE.Color(0x1D2742); // Background color

    // Load GLTF model
    const loader = new GLTFLoader();
    let gltfObject: THREE.Object3D;

    loader.load("model/scene.gltf", (gltf) => {
      gltfObject = gltf.scene;
      scene.add(gltfObject);

      // Position the model
      gltfObject.position.set(1, 1, 1);

      // Create directional light (sunlight)
      const sunlight = new THREE.DirectionalLight(0xffdddd, 13.5);
      sunlight.position.set(1, 3, 1);
      scene.add(sunlight);

      // Apply metal material to the model
      const metalMaterial = new THREE.MeshStandardMaterial({
        color: 'skyblue', // Metal color
        roughness: 0.1,  // Glossiness of the metal
        metalness: 0.9, // Metalness of the material
      });

      // Traverse through child objects (meshes) and apply the metal material
      gltfObject.traverse((child) => {
        if (child instanceof THREE.Mesh) {
          child.material = metalMaterial;
        }
      });

      // Set camera position
      camera.position.set(5, 4, 4);

      // Define the center of rotation
      const center = new THREE.Vector3(1, 1, 1);

      // Set the radius and angle for circular motion
      const radius = 6; // Adjust the radius of the circle
      let angle = 0;

      // Animation loop
      const animate = () => {
        requestAnimationFrame(animate);

        // Calculate new camera position for circular motion
        const orbitSpeed = 0.04;
        angle += orbitSpeed;
        camera.position.z = center.y + radius * Math.sin(angle);
        camera.position.z = center.x + radius * Math.cos(angle);
        camera.lookAt(center);

        // Render the scene
        if (rendererRef.current) {
          rendererRef.current.render(scene, camera);
        }
      };

      animate();
    });
  }, []);

  return (
    <div ref={containerRef} style={{ position: 'fixed' }} />
  );
};

export default MyThreeComponent;
